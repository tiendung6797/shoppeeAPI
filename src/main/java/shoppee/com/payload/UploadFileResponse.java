package shoppee.com.payload;

public class UploadFileResponse {
	private String file_name;
	private String fileDownloadUri;
	private String file_type;
	private long size;

	public UploadFileResponse(String file_name, String fileDownloadUri, String file_type, long size) {
		super();
		this.file_name = file_name;
		this.fileDownloadUri = fileDownloadUri;
		this.file_type = file_type;
		this.size = size;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
