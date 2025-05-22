<div align="center"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhQegTkkOzk4HM8bhNZwV5PprBTmhPrj5org&s"></div>
<h1 align="center">ĐỒ ÁN MÔN LẬP TRÌNH JAVA</h1>
<p align="center"><strong>Ứng dụng quản lý nhà sách</strong>
<br>Dự án phát triển bởi nhóm RGBunny thuộc trường đại học UIT</p>

## 📊 Thông tin nhóm phát triển

<div align="center">
  <table>
    <tr>
      <th align="center">Thành viên</th>
      <th align="center">MSSV</th>
      <th align="center">Github</th>
    </tr>
    <tr>
      <td align="center">Đinh Nhật Thông</td>
      <td align="center">23521522</td>
      <td align="center">https://github.com/AkaDNT</td>
    </tr>
    <tr>
      <td align="center">Lê Trường Hòa</td>
      <td align="center">23520504</td>
      <td align="center">https://github.com</td>
    </tr>
    <tr>
      <td align="center">Lê Nguyễn Minh Thư</td>
      <td align="center">23521539</td>
      <td align="center">https://github.com</td>
    </tr>
    <tr>
      <td align="center">Lê Anh Thư</td>
      <td align="center">23521536</td>
      <td align="center">https://github.com</td>
    </tr>
  </table>
</div>

## 🖼 Demo ứng dụng
![Ảnh mẫu](https://insidebitcoins.com/wp-content/uploads/2024/07/GTD1UkJaoAAVAUC.jpeg)
<div align="center">*Ảnh minh họa*</div>

<div align="center"><img src="demo.gif"></img></div>

## 📌 Giới thiệu
Ứng dụng được phát triển nhằm hỗ trợ việc quản lý các nhà sách hiện nay. Một số chức năng chính:
1. Quản lý sách
- Thêm, chỉnh sửa, xóa sách
- Nhập liệu thông tin: tên sách, mô tả, tác giả, thể loại, giá bán,…

2. Quản lý khách hàng
- Danh sách người dùng, phân quyền (Admin / Khách hàng)
- Xem lịch sử mua hàng của từng khách

3. Báo cáo – thống kê
- Thống kê doanh thu theo ngày/tháng/năm

4. Giỏ hàng & thanh toán
-Cho phép khách hàng thêm sách vào giỏ
-Hỗ trợ thanh toán online hoặc thanh toán khi nhận hàng

5. Xác thực và phân quyền
- Đăng ký, đăng nhập, bảo mật bằng JWT
- Giao diện và quyền truy cập khác nhau theo vai trò

6. Xác thực email bằng send grid:
- Quên mật khẩu xác thực qua email.

**Tiến độ hiện tại**: Đã hoàn thành 70% chức năng chính

## 🎯 Mục tiêu & Yêu cầu

** 💡 Yêu cầu hệ thống:**
- Java JDK 19
- NextJS 15

## ⚙ Cài đặt
**1. Clone repository**
```bash
git clone https://github.com/AkaDNT/IS216-JAVA.git
```
**2. Cấu hình Backend**
```bash
cd Backend/Bookstore

# Khôi phục dependencies maven

# Thiết lập application.properties (Backend/Bookstore/Bookstore/src/main/resources/application.properties)
spring.application.name=Bookstore

spring.datasource.url=jdbc:oracle:thin:@localhost:1521:orcl
spring.datasource.username=your-user-name
spring.datasource.password=your-password
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect

spring.app.jwtSecret=your-secret-key
spring.app.jwtExpirationMs=3000000



logging.level.org.springframework=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.springframework.security=DEBUG
logging.level.com.rgbunny=DEBUG

app.reset-password-url=http://localhost:3000/reset-password
from.email=your-email

# Email config
spring.mail.host=smtp.sendgrid.net
spring.mail.port=587
spring.mail.username=your-sendgrid-username
spring.mail.password=your-sendgrid-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true


# Thiết lập database
Chạy các request đến endpoints trong controller theo địa chỉ http://localhost:8080/api/* hoặc tạo data bằng SQL server 

# Chạy ứng dụng
```
**3. Cấu hình Frontend**
```bash
cd frontend/webapp

# Cài đặt dependencies
npm install

# Tạo file .env
API_URL = "http://localhost:8080/api"
NEXT_PUBLIC_BASE_URL="http://localhost:3000"

# Khởi động dev server
npm run dev

## 📈 Trạng thái dự án
🟢 Đang phát triển tích cực  

## 🙏 Ghi nhận
- Người hướng dẫn: Giảng viên Tạ Việt Phương
