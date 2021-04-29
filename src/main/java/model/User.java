package model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public abstract class User {
    protected String surname;
    protected String _name;
    protected String birthDate;
    protected String address;

}
