package web.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {

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
        String uploadPath = "C:\\Users\\tj-bu-703-008\\Desktop\\tj_2024A_spring\\src\\main\\resources\\static\\upload\\";
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

    // 2. 파일 다운로드


}   // class end
