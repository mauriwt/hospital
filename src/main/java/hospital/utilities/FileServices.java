package hospital.utilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServices {

  public String uploadMultipleFiles(MultipartFile[] files) {
    String fileName = null;
    StringBuilder message = new StringBuilder();
    String absoluteFilePath = "";
    String workingDirectory = System.getProperty("user.dir");
    
    if (files != null && files.length > 0) {
      
      for (int i = 0; i < files.length; i++) {
        
        absoluteFilePath = workingDirectory + File.separator + fileName;
        try (BufferedOutputStream buffStream = new BufferedOutputStream(
            new FileOutputStream(new File(absoluteFilePath)))){
          fileName = files[i].getOriginalFilename();
          byte[] bytes = files[i].getBytes();
          buffStream.write(bytes);
          message.append("You have successfully uploaded ");
          message.append(fileName);
          message.append(" in the directoty ");
          message.append(absoluteFilePath);
          message.append(" \n");
        } catch (Exception e) {
          message.append("You failed to upload ");
          message.append(fileName);
          message.append(": ");
          message.append(e.getMessage());
          message.append("\n");
        }
      }
    } else {
      message.append("Unable to upload. File is empty.");
    }
    return message.toString();
  }

}
