package web.service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.UUID;

@Service
public class FileService {

    // 0. 파일이 저장된 위치 경로 정의 필드
    String uploadPath = "C:\\Users\\tj-bu-703-008\\Desktop\\tj_2024A_spring\\src\\main\\resources\\static\\upload\\";

    // 1. 파일 업로드 :
        // 매개변수로 파일의 바이트가 저장된 MultipartFile 인터페이스
        // 업로드된 파일명 반환
    public String fileUpload(MultipartFile multipartFile){

        System.out.println(multipartFile.getContentType()); // 파일의 확장자
        System.out.println(multipartFile.getName());    // 속성명
        System.out.println(multipartFile.getSize());    // 파일의 바이트 사이즈/용량
        System.out.println(multipartFile.isEmpty());    // 파일이 없으면 true 있으면 false

        // 1. 첨부 파일의 실제 이름 추출
            // + 클라이언트(유저)들이 서로 다른 파일내용의 같은 파일명으로 업로드했을 때 식별이 불가능.
            // 해결방안 : 1. UUID(고유성을 보장하는 ID 규약) 2. 조합식별 설계(주로 업로드 날짜시간과 파일명 조합 , 게시물번호)
        String uuid = UUID.randomUUID().toString(); // 난수의 UUID 생성 , 임의의 UUID 규약에 따른 문자열 생성
        System.out.println("uuid = " + uuid);
        String fileName = multipartFile.getOriginalFilename();
            // UUID + 파일명 합치기 , uuid와 파일명 구분하는 문자 조합 , 파일명에 _(언더바)가 존재하면 안된다.
            // 추후에 _(언더바) 기준으로 분해하면 [0]UUID , [1]순수파일명
            // "문자열".replaceAll("기존문자" , "새로운문자") : 만약에 문자열내 기존문자가 존재하면 새로운문자로 치환해서 반환
        fileName = uuid + "_" + fileName.replaceAll("_" , "-"); // fileName에 '_'가 존재하면 '-' 문자로 변경
        System.out.println("fileName = " + fileName);
        // 2. 첨부파일 저장/복사/이동할 경로 만들기

        // 3. 첨부파일 저장/복사/이동할 경로와 파일명 합치기
        String filePath = uploadPath + fileName;
        // 4. 해당 경로로 설정한 file 객체 , transferTo(file 객체)
        File file = new File(filePath);
        // 5. transferTo(file 객체) : file 객체 내 설정한 해당 경로로 파일 복사/저장/이동
            // 일반 예외 무조건 발생
        try {
            multipartFile.transferTo(file);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
        return fileName;
    }   // fileUpload() end

    @Autowired
    private HttpServletRequest request;     // HTTP 요청 객체 , HTTP로 요청 들어온 정보와 기능이 포함된 객체
    @Autowired
    private HttpServletResponse response;   // HTTP 응답 객체, HTTP로 응답 할때 정보와 기능이 포함된 객체

    // 2. 파일 다운로드
    public void fileDownLoad(String filename){
        System.out.println("FileService.fileDownLoad");
        System.out.println("filename = " + filename);
        // 1. 다운로드할 경로 설정 uploadPath
            // 1. 업로드된 경로와 다운로드할 파일명 조합
        String downloadPath = uploadPath + filename;
        // - File 클래스는 file 관련된 다양한 메소드 제공 ,
            // - exists(); : 해당 경로에 파일이 존재하면 true , 없으면 false 반환
            // - length(); : 해당 경로의 파일이 존재하면 파일의 용량을 바이트의 개수로 반환 (용량찾기)
        File file = new File(downloadPath);
        if(!file.exists()){return;} // 파일이 존재하지 않으면
        System.out.println("downloadPath = " + downloadPath);
        // 2. 해당 다운로드할 파일을 서버(자바)로 바이트 읽어들이기 왜? 서버가 파일을 읽어와야 하는지?
            // - 스트림이란 : 자바 외부와 통신 시 바이트가 다니는 통로
            // - InputStream : 읽어들이는 통로 , OutStream : 내보내는 통로
            // - Buffered, 버퍼 : 특정위치로 이동하는 동안 일시적으로 데이터를 보관하는 메모리(스트림에서도 사용)
        try {
            // =================== 파일을 바이트 배열로 읽어오기 ================================= //
            // 2-1 파일 입력 스트림 객체 생성 , new BufferedInputStream(입력한 대상의 스트림 객체)
            // BufferedInputStream fin = new BufferedInputStream(new FileInputStream(downloadPath));
            FileInputStream fin = new FileInputStream(downloadPath);
            // 2-2 파일의 용량만큼 배열 선언
                // 파일의 용량
            long fileSize = file.length();
                // 파일의 용량만큼 배열의 길이 선언
            byte[] bytes = new byte[(int)fileSize];
                // .read(배열명) : 해당 파일을 읽어서 바이트들을 해당 배열에 하나씩 대입한다.
            fin.read(bytes); // 경로에 해당하는 파일을 바이트로 가져오기
            fin.close();
            System.out.println(Arrays.toString(bytes));
            // =================== 읽어온 바이트 배열을 HTTP 바이트 형식으로 응답하기 ================================= //
            // 3. HTTP 스트림으로 응답하기
                // 3-1 출력 스트림 객체 생성 , new BufferedOutputStream(출력할 대상의 스트림 객체)
                    // (response.getOutputStream() : HTTP 응답할 때 바이트 형식의 스트림 사용
            // BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream());
            ServletOutputStream fout = response.getOutputStream();

            // --- HTTP응답에 헤더 속성 추가 , .setHeader(key , value)
                // Content-Disposition : 브라우저가 제공하는 다운로드 형식
                // attachment;filename="다운로드에 표시될 파일명"
                // - URLEncoder.encode() : URL 경로상의 한글을 인코딩
                // filename.split("_")[1] : '_' 기준으로 분해해서 UUID를 제외한 파일명만 추출
            response.setHeader("Content-Disposition" , "attachment;filename="+ URLEncoder.encode(filename.split("_")[1] , "UTF-8"));
            // 3-2 바이트배열 내보내기 / 출력 / 쓰기
            fout.write(bytes);
            // - 버퍼 닫기
            fout.close();
        }catch (Exception e){
            System.out.println("e = " + e);
        }

    }   // fileDownLoad() end

}   // class end
