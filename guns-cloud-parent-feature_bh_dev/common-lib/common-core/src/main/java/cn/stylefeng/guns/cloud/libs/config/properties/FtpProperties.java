package cn.stylefeng.guns.cloud.libs.config.properties;

import lombok.Data;

@Data
public class FtpProperties {
    private String address = "192.168.0.216";
    private Integer port=21;
    private String username="DwSelect";
    private String password="Jinchang001";

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
