package com.bespalov.nail_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private List<ProcedureDto> procedureList;

    public MasterDto(UUID id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MasterDto masterDto = (MasterDto) o;
        return Objects.equals(id, masterDto.id) && Objects.equals(firstName, masterDto.firstName) && Objects.equals(lastName, masterDto.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
