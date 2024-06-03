package ev.eval_course_a_pied.services;

import ev.eval_course_a_pied.entity.*;
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

    public ClassementService(EtapeRepository etapeRepository,
                             CoureurRepository coureurRepository,
                             EquipeRepository equipeRepository) {
        this.etapeRepository = etapeRepository;
        this.coureurRepository = coureurRepository;
        this.equipeRepository = equipeRepository;
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
        List<Etape> etapes = etapeRepository.findAllEtapes();
        List<Equipe> equipe = equipeRepository.findAll();
        //key : equipe
        // value : points
        HashMap<Integer,Double> points = new HashMap<>();
        for (int i = 0; i < etapes.size(); i++) {
            System.out.println("i = "+i+" "+ etapes);
            List<Object[]> pointsEtape = equipeRepository.getPointsEquipeParEtape(etapes.get(i).getId());
            for (int j = 0; j < pointsEtape.size(); j++) {
                int equipeId = Integer.valueOf(String.valueOf(pointsEtape.get(j)[1]));
                double pointsEquipe = Double.valueOf(String.valueOf(pointsEtape.get(j)[0]));
                System.out.println("equipeid="+equipeId+" points = "+ pointsEquipe);
                if (points.containsKey(equipeId)) {
                    points.put(equipeId, points.get(equipeId) + pointsEquipe);
                } else {
                    points.put(equipeId, pointsEquipe);
                }
            }
        }
        for (int i = 0; i < equipe.size(); i++) {
            if(!points.containsKey(equipe.get(i).getId())){
                points.put(equipe.get(i).getId(),0.0);
            }
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


//    public List<List<ClassementCoureurParEtape>> getToutLesClassementParEtape(){
//        List<Etape> etapes = etapeRepository.findAllEtapes();
//        List<List<ClassementCoureurParEtape>> listeClassement
//    }
}
