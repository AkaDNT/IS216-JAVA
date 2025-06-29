package com.rgbunny.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "App_User", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_name"),
        @UniqueConstraint(columnNames = "email")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Size(min = 2, max = 20, message = "Tên người dùng phải lớn hơn 2 và nhỏ hơn 20 ký tự!")
    @Column(name = "user_name")
    private String userName;

    @NotBlank
    @Size(max = 50, message = "Email không được vượt quá 50 ký tự!")
    @Email(message = "Email không đúng định dạng!")
    @Column(name = "email")
    private String email;

    @Size(min = 9, max = 15, message = "Số điện thoại phải có độ dài từ 9 đến 15 ký tự!")
    @Column(name = "phone_number")
    private String phoneNumber;


    @NotBlank
    @Size(max = 100, min = 6, message = "Mật khẩu phải lớn hơn 6 và nhỏ hơn 100 ký tự!")
    @Column(name = "password")
    private String password;

    @Setter
    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Getter
    @Setter
    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
//    @JoinTable(name = "user_address",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addresses = new ArrayList<>();

    @ToString.Exclude
    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Cart cart;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Column(name = "reset_password_expiry")
    private Date resetPasswordExpiry;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
