<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tải Lên Tệp</title>
</head>
<body>
    <h1>Servlet Multipart</h1>
    <form action="${pageContext.request.contextPath}/multiPartServlet" method="post" enctype="multipart/form-data">

        <label for="fullname">Họ và tên <span class="require">*</span></label><br />
        <input type="text" class="form-control" id="fullname" name="fullname" required /> <br /><br />
        
        <label for="phone">Số điện thoại <span class="require">*</span></label><br />
        <input type="text" class="form-control" id="phone" name="phone" required /> <br /><br />
        
        <label for="fileUpload">Chọn tệp để tải lên:</label><br />
        <input type="file" name="fileUpload" id="fileUpload" required /> <br /><br />
        <input type="submit" value="Tải lên" />
    </form>
</body>
</html>
