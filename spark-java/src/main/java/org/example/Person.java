package org.example;

import java.util.Objects;

public record Person(String firstName, String lastName) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(firstName(), person.firstName()) && Objects.equals(lastName(), person.lastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName(), lastName());
    }
}
