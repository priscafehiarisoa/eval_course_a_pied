package ev.eval_course_a_pied.services;

import ev.eval_course_a_pied.entity.*;
import ev.eval_course_a_pied.repository.CategorieRepository;
import ev.eval_course_a_pied.repository.CoureurRepository;
import ev.eval_course_a_pied.repository.EquipeRepository;
import ev.eval_course_a_pied.repository.EtapeRepository;
import ev.eval_course_a_pied.utils.Utils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
public class ClassementService {

    private final EtapeRepository etapeRepository;
    private final CoureurRepository coureurRepository;
    private final EquipeRepository equipeRepository;
    private final CategorieRepository categorieRepository;

    public ClassementService(EtapeRepository etapeRepository,
                             CoureurRepository coureurRepository,
                             EquipeRepository equipeRepository,
                             CategorieRepository categorieRepository) {
        this.etapeRepository = etapeRepository;
        this.coureurRepository = coureurRepository;
        this.equipeRepository = equipeRepository;
        this.categorieRepository = categorieRepository;
    }

    public List<ClassementCoureurParEtape> getClassementParEtape(int etape){
        List<Object[]> object = etapeRepository.getClassementParIdEtape(etape);
        List<ClassementCoureurParEtape> classement = new ArrayList<>();
        for (int i = 0; i < object.size(); i++) {
            double points = Double.valueOf(String.valueOf(object.get(i)[0]));
            int rang = Integer.valueOf(String.valueOf(object.get(i)[1]));
            int idCoureur = Integer.valueOf(String.valueOf(object.get(i)[6]));
            int idEtape = Integer.valueOf(String.valueOf(object.get(i)[7]));
            Coureur coureur = coureurRepository.findById(idCoureur).orElse(null);
            Etape etape1 = etapeRepository.findById(idEtape).orElse(null);
            Duration durree=Utils.parseDuration(String.valueOf(object.get(i)[2]));
            classement.add(new ClassementCoureurParEtape(durree,points,rang,coureur,etape1));
        }
        return classement;
    }

    public HashMap<Integer,Double> getAllPointsEquipe(){
        List<Object[]> pointsEtape = equipeRepository.getPointsEquipeParEtape();
        //key : equipe
        // value : points
        HashMap<Integer,Double> points = new HashMap<>();
        for (int i = 0; i < pointsEtape.size(); i++) {
            int equipeId = Integer.valueOf(String.valueOf(pointsEtape.get(i)[1]));
            double pointsEquipe = Double.valueOf(String.valueOf(pointsEtape.get(i)[0]));
            points.put(equipeId,pointsEquipe);
        }
        return points;
    }

    public List<ClassementEquipe> getClassementEquipe(){
        HashMap<Integer,Double> pointsEquipes = getAllPointsEquipe();
        List<ClassementEquipe> classementEquipes= new ArrayList<>();
        Set<Integer> setEquipeId= pointsEquipes.keySet();
        for (Integer idEquipe : setEquipeId) {
            Equipe equipe = equipeRepository.findById(idEquipe).orElse(null);
            double points = pointsEquipes.get(idEquipe);
            classementEquipes.add( new ClassementEquipe(equipe,points));
        }
        Collections.sort(classementEquipes);
        for (int i = 0; i < classementEquipes.size(); i++) {
            classementEquipes.get(i).setRang(i+1);
        }
        if(checkIfAllNull(classementEquipes)){
            return new ArrayList<ClassementEquipe>();
        }
        return classementEquipes;
    }

    public boolean checkIfAllNull (List<ClassementEquipe> classementEquipes){
        int count = 0;
        for (int i = 0; i < classementEquipes.size(); i++) {
            if(classementEquipes.get(i).getPoints()==0){
                count++;
            }
        }
        if(count== classementEquipes.size()){
            return true;
        }
        return false;
    }

    public List<ClassementEquipe> classementEquipeParCategorie (int categorie){
        List<Object[]> objects = equipeRepository.getPointsEquipeParCategorieId(categorie);
        List<ClassementEquipe> classement = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            Equipe equipe = equipeRepository.findById(Integer.valueOf(String.valueOf(objects.get(i)[2]))).orElse(null);
            ClassementEquipe classementEquipe = new ClassementEquipe(equipe,Double.valueOf(String.valueOf(objects.get(i)[0])));
            Categorie categorie1= categorieRepository.findById(Integer.valueOf(String.valueOf(objects.get(i)[1]))).orElse(null);
            classementEquipe.setCategorie(categorie1);
            classement.add(classementEquipe);
        }
        return classement;
    }


}
