package entities;

public class Person {
    
    private String name;
    private String cpf;

    public Person() {
        super();
    }

    public Person(String name) {
        super();
        this.name = name;
    }

    public Person(String name, String cpf) {
        super();
        this.name = name;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }
}
