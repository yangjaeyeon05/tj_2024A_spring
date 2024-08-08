package web.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ProductDto {

    private int pno;
    private String ptitle;
    private String pcontent;
    private int pprice;
    private String pdate;
    private int pview;
    // 여러개 첨부파일 필드
    List<MultipartFile> files;
    // 여러개 파일명 필드
    List<String> fileNames;

}   // class end
