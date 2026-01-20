package by.epam.spring.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Revision")
@Table(name = "revision")
@RevisionEntity
public class Revision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private Integer id;

    @RevisionTimestamp
    private Long timestamp;


}
