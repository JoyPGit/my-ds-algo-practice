package Core_Java;

class Animal_superClass {
    int superVar; 
    Animal_superClass(int a){
        this.superVar = a;
    }

    public void eat(){
        System.out.println("eating in animal super");
    }

    public void roam(){
        System.out.println("roaming");
    }


}