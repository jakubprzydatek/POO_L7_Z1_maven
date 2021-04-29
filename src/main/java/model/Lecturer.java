package model;

public class Lecturer extends User{

    @Override
    public String toString() {
        return surname + _name + birthDate + address;
    }
}
