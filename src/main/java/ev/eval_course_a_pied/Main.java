package ev.eval_course_a_pied;

import ev.eval_course_a_pied.entity.*;
import ev.eval_course_a_pied.entity.user.Role;
import ev.eval_course_a_pied.entity.user.UserModel;
import ev.eval_course_a_pied.repository.*;
import ev.eval_course_a_pied.repository.userRepository.RoleRepository;
import ev.eval_course_a_pied.services.ClassementService;
import ev.eval_course_a_pied.services.auth.RegisterService;
import ev.eval_course_a_pied.utils.Statics;
import ev.eval_course_a_pied.utils.Utils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Main {

    private final RoleRepository roleRepository;
    private final RegisterService registerService;
    private final EquipeRepository equipeRepository;
    private final EtapeRepository etapeRepository;
    private final CategorieRepository categorieRepository;
    private final GenreRepository genreRepository;
    private final ClassementService classementService;

    public Main(RoleRepository roleRepository, RegisterService registerService,
                EquipeRepository equipeRepository,
                EtapeRepository etapeRepository,
                CategorieRepository categorieRepository,
                GenreRepository genreRepository, ClassementService classementService) {
        this.roleRepository = roleRepository;
        this.registerService = registerService;
        this.equipeRepository = equipeRepository;
        this.etapeRepository = etapeRepository;
        this.categorieRepository = categorieRepository;
        this.genreRepository = genreRepository;
        this.classementService = classementService;
    }

    @Bean
    CommandLineRunner commandLineRunner (CoureurRepository coureurRepository,
                                         CoureurEtapeRepository coureurEtapeRepository,
                                         TempResultatRepository tempResultatRepository){
        return args -> {
            // role Equipe
//            // create equipe
//            List<Equipe> equipes  = new ArrayList<>();
//            UserModel ue1= registerService.register(new UserModel("equipe1","equipe1"),null);
//            equipes.add(new Equipe("equipe1",ue1));
//
//            UserModel ue2= registerService.register(new UserModel("equipe2","equipe2"),null);
//            equipes.add(new Equipe("equipe2",ue2));
//
//            UserModel ue3= registerService.register(new UserModel("equipe3","equipe3"),null);
//            equipes.add(new Equipe("equipe3",ue3));
//            etapeRepository.deleteAll();
//            equipeRepository.saveAll(equipes);
//
//            // create etqpes
//
//            List<Etape> etapes = new ArrayList<>();
//            etapes.add(new Etape(1,"Andaboy",Statics.INIT_STATE,5.0, LocalDateTime.now()));
//            etapes.add(new Etape(2,"Mitsinjo",Statics.INIT_STATE,5.0, LocalDateTime.now()));
//            etapes.add(new Etape(3,"sans fil",Statics.INIT_STATE,5.0, LocalDateTime.now()));
////            etapeRepository.deleteAll();
//            etapeRepository.saveAll(etapes);
//
//            // list categories
//            List<Categorie> categories= new ArrayList<>();
//            categories.add(new Categorie("homme"));
//            categories.add(new Categorie("femme"));
//            categories.add(new Categorie("junior"));
//            categories.add(new Categorie("senior"));
////            categorieRepository.deleteAll();
//            categorieRepository.saveAll(categories);
//
//            //list genre
//            List<Genre> genres= new ArrayList<>();
//            genres.add(new Genre("homme"));
//            genres.add(new Genre("femme"));
////            genreRepository.deleteAll();
//            genreRepository.saveAll(genres);
//
//            // create coureurs
//            List<Coureur> coureurs = new ArrayList<>();
////            equipe 1
//            coureurs.add(new Coureur("vali", Utils.parseAnyStringToLocalDate("2001-01-01"),1,equipes.get(0),List.of(),genres.get(1)));
//            coureurs.add(new Coureur("judith", Utils.parseAnyStringToLocalDate("2006-01-01"),2,equipes.get(0),List.of(),genres.get(1)));
//            coureurs.add(new Coureur("nasine", Utils.parseAnyStringToLocalDate("2000-01-01"),3,equipes.get(0),List.of(),genres.get(0)));
//            coureurs.add(new Coureur("harisoa", Utils.parseAnyStringToLocalDate("1989-01-01"),4,equipes.get(0),List.of(),genres.get(1)));
////            equipe 2
//            coureurs.add(new Coureur("ravo hary", Utils.parseAnyStringToLocalDate("2001-01-01"),1,equipes.get(1),List.of(),genres.get(0)));
//            coureurs.add(new Coureur("finaritra", Utils.parseAnyStringToLocalDate("2006-01-01"),2,equipes.get(1),List.of(),genres.get(0)));
//            coureurs.add(new Coureur("elihary", Utils.parseAnyStringToLocalDate("2000-01-01"),3,equipes.get(1),List.of(),genres.get(1)));
//            coureurs.add(new Coureur("faly hary", Utils.parseAnyStringToLocalDate("1989-01-01"),4,equipes.get(1),List.of(),genres.get(0)));
////            equipe 3
//            coureurs.add(new Coureur("manohy", Utils.parseAnyStringToLocalDate("2001-01-01"),1,equipes.get(2),List.of(),genres.get(0)));
//            coureurs.add(new Coureur("lahatra", Utils.parseAnyStringToLocalDate("2006-01-01"),2,equipes.get(2),List.of(),genres.get(1)));
//            coureurs.add(new Coureur("mializo", Utils.parseAnyStringToLocalDate("2000-01-01"),3,equipes.get(2),List.of(),genres.get(1)));
//            coureurs.add(new Coureur("aina", Utils.parseAnyStringToLocalDate("1989-01-01"),4,equipes.get(2),List.of(),genres.get(1)));
//            coureurRepository.saveAll(coureurs);

//            Coureur coureur = coureurRepository.findById(6).orElse(null);
//            Etape etape = etapeRepository.findById(2).orElse(null);
//            System.out.println(coureurEtapeRepository.existsCoureurEtapeByCoureurAndEtape(coureur,etape));

//            TEST CLASSEMENT
//            classementService.getClassementParEtape(2).forEach(System.out::println);

//            System.out.println(classementService.getAllPointsEquipe());
//            classementService.getClassementEquipe().forEach(System.out::println);
            tempResultatRepository.getDistinctEquipe().forEach(System.out::println);
        };
    }
}
