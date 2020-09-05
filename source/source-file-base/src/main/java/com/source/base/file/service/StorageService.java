package com.source.base.file.service;

import com.base.dtos.upload.UploadDTO;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileUrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

/**
 * Service lưu trữ file
 *
 * @author Nguyễn Đình Tạo
 */
@Component
public class StorageService {

    private final String PATH = "system.path.upload";
    private final String PATH_TEMP = "system.path.upload.temps";

    private final Environment env;

    public StorageService(Environment env) {
        this.env = env;
    }

    /**
     * Lấy tên loại file từ {@link MultipartFile}
     *
     * @param file nguồn
     * @return tên loại file
     */
    public String getType(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length())
                .toLowerCase();
    }

    /**
     * Kiểm tra có tồn tại theo danh sách loại file
     *
     * @param typeCheck loại file cần kiểm tra
     * @param types     danh sách loại file
     * @return true/false
     */
    public boolean checkFileType(String typeCheck, String[] types) {
        for (String t : types) {
            if (typeCheck.equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Kiểm tra có tồn tại theo danh sách loại file từ file {@link MultipartFile}
     *
     * @param file  nguồn
     * @param types danh sách loại file
     * @return true/false
     */
    public boolean checkFileType(MultipartFile file, String[] types) {
        return !checkFileType(getType(file), types);
    }

    /**
     * Kiểm tra đường dẫn file có tồn tại
     *
     * @param pathUpload đường dẫn file
     * @throws IOException lỗi khi không tìm thấy hoặc không có quyền truy cập file
     */
    public void checkDirectoryExist(String pathUpload) throws IOException {
        Path path = Paths.get(pathUpload);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    /**
     * Lưu file theo đường dẫn file và từ file {@link MultipartFile}
     *
     * @param pathUpload đường dẫn file
     * @param file       nguồn
     * @throws IOException lỗi khi không lưu được file
     */
    public void saveFile(String pathUpload, MultipartFile file) throws IOException {
        Files.write(Paths.get(pathUpload), file.getBytes());
    }

    /**
     * Xóa file theo đường dẫn file
     *
     * @param localFile đường dẫn file
     * @throws IOException lỗi khi không xóa được file
     */
    public void deleteFile(String localFile) throws IOException {
        Files.deleteIfExists(Paths.get(localFile));
    }

    /**
     * Lưu trữ file
     *
     * @param pathUpload đường dẫn file upload
     * @param username   tên người tải lên
     * @param temp       file tạm
     * @param file       tải lên
     * @return thông tin file sau khi lưu
     * @throws IOException lỗi
     */
    public UploadDTO store(String pathUpload, String typeFile, String username, boolean temp, MultipartFile file) throws IOException {
        // create directory if not exist
        Calendar calendar = Calendar.getInstance();
        String path = "/" + typeFile + ((username != null) ? "/auth" : "");
        path += "/" + calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DATE);
        // create directory if not exist
        this.checkDirectoryExist(pathUpload + path);
        // set path files
        String type = getType(file);
        path += "/" + (username != null ? (username + "-" + "-") : "") + UUID.randomUUID().toString().replaceAll("-", "") + "." + type;
        // save File
        this.saveFile(pathUpload + path, file);
        // Create Upload Class
        UploadDTO uploadDTO = new UploadDTO();
        uploadDTO.setUsername(username);
        uploadDTO.setCreated(calendar.getTime());
        uploadDTO.setTemp(temp);
        if (temp) {
            calendar.add(Calendar.DATE, 7);
            uploadDTO.setExpiredTime(calendar.getTime());
        }
        uploadDTO.setFileName(file.getOriginalFilename());
        uploadDTO.setPath(path);
        uploadDTO.setSize(file.getSize());
        uploadDTO.setType(type);
        return uploadDTO;
    }

    /**
     * Lưu trữ file
     *
     * @param username tên người tải lên
     * @param file     tải lên
     * @return thông tin file đã lưu
     * @throws IOException lỗi
     */
    public UploadDTO storeFile(String typeFile, String username, MultipartFile file) throws IOException {
        return this.store(env.getProperty(PATH), typeFile, username, false, file);
    }

    /**
     * Lưu trữ file tạm thời
     *
     * @param username tên người tải lên
     * @param file     tải lên
     * @return thông tin file đã lưu
     * @throws IOException lỗi
     */
    public UploadDTO storeTempFile(String typeFile, String username, MultipartFile file) throws IOException {
        return this.store(env.getProperty(PATH_TEMP), typeFile, username, true, file);
    }

    /**
     * Download file
     *
     * @param path đường dẫn đến file
     * @param temp file tạm
     * @return Định dạng file
     * @throws IOException lỗi
     */
    public FileUrlResource getResource(String path, boolean temp) throws IOException {
        if (temp) {
            return new FileUrlResource(env.getProperty(PATH_TEMP) + path);
        }
        return new FileUrlResource(env.getProperty(PATH) + path);
    }
}