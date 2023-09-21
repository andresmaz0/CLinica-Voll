package med.voll.api.domain.medico;

import org.springframework.data.jpa.repository.JpaRepository;

//Esta interfaz tiene como objetivo simplificar los metodos para realizar CRUD
public interface MedicoRepository extends JpaRepository<Medico, Long>{
    // en los <> el medico es el tipo de objeto que deseo guardar y Long es el tipo de objeto del Id
}
