package com.zgx.basic;


public class Person {
    protected String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Person(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getClass() == obj.getClass()){
            Person p = (Person)obj;
            if(p.getName() == null || name == null){
                return false;
            }
            else{
                return name.equalsIgnoreCase(p.getName());
            }
        }
        return false;
    }
}
