/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mydisk.model;

/**
 * 
 * @author young
 */
public class Disk {
    private String name;//disk user name
    private String password;//password
    private String type;//disk type
   
    public Disk(){}
    public Disk(String name,String type) {
        this.name=name;
        this.type=type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
