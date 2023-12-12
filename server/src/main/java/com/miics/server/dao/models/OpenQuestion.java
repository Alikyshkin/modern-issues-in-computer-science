package com.miics.server.dao.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class OpenQuestion extends Question{
    private String answer;
}
