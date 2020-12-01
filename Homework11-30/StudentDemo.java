class Student{
    String name;
    String sno;
    
    void printName(){
        System.out.println(this.name);
    }
    
    void printSno(){
        System.out.println(this.sno);
    }
}

class StudentDemo{
    public static void main(String args[]) {
        Student student1=new Student();
        Student student2=new Student();
        
        student1.name="王悉奥";
        student2.name="小林樱见";
        
        student1.sno="2018302120081";
        student2.sno="2020300120001";
        
        student1.printName();
        student1.printSno();
        
        student2.printName();
        student2.printSno();
    }
}