
# Spring Boot File Upload

## Example With MuiltipartFile
### Uploading single file
```java
    @RequestMapping(method = RequestMethod.POST, value = "/video/{id}/data"/*, consumes = MediaType.MULTIPART_FORM_DATA_VALUE*/)  
    public VideoStatus setVideoData(@PathVariable("id") long id, @RequestPart("data") MultipartFile videoData) throws IOException {  
	  return videoService.saveVideoFile(id, videoData.getBytes());  
    }
```

### Uploading multiplefiles
```java
@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
public  @ResponseBody  List<String> uploadMultipleFileHandler(@RequestParam("name") String[] names, @RequestParam("file") MultipartFile[] files) {

	return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());

}
```

## Example with HttpServletRequest
```java
@GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
```


## References
[spring-boot-file-upload-download-rest-api-example](https://www.callicoder.com/spring-boot-file-upload-download-rest-api-example/)
[spring-boot-file-upload-download-jpa-hibernate-mysql-database-example](https://www.callicoder.com/spring-boot-file-upload-download-jpa-hibernate-mysql-database-example/)
