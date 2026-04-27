package com.xu.code.practice.entity;

public class GeneralEntity {

    /**
     * @Author liberty
     * @Date 2024/10/10 22:06
     */
    private Integer key;
    private String value;

    public Integer getKey(){
        return this.key;
    }

    public void setKey(Integer key){
        this.key = key;
    }

    public String getValue(){
        return this.value;
    }
    public void setValue(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "GeneralEntity=" + "{" + "\n"
                + "key=" + this.key + "," + "\n"
                + "value=" + this.value + "\n"
                + "}";
    }

    public static boolean isPalindrome(String s) {
        if(s == null || s.length() == 0)
            return true;
        String res = s.trim().toLowerCase().replaceAll("[^a-zA-Z]", "");
        if(res.length() < 2)
            return true;
        int left = 0;
        int right = res.length() - 1;
        while(left < right){
            if(res.charAt(left++) != res.charAt(right--))
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        GeneralEntity generalEntity = new GeneralEntity();
        generalEntity.setKey(10);
        generalEntity.setValue("100");
        System.out.println(generalEntity.toString());

    }
}
