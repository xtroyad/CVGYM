//package com.cvgym.CVGYM.dataLoader;
//
//import com.cvgym.CVGYM.coach.Coach;
//import com.cvgym.CVGYM.coach.CoachRepository;
//import com.cvgym.CVGYM.courseSet.Course;
//import com.cvgym.CVGYM.courseSet.CourseRepository;
//import com.cvgym.CVGYM.gym.Gym;
//import com.cvgym.CVGYM.gym.GymRepository;
//import com.cvgym.CVGYM.manager.Manager;
//import com.cvgym.CVGYM.manager.ManagerRepository;
//import com.cvgym.CVGYM.question.Question;
//import com.cvgym.CVGYM.question.QuestionRepository;
//import com.google.gson.Gson;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//@Component
//public class MyApplicationRunner implements ApplicationRunner {
//    @Autowired
//    private GymRepository gymService;
//    @Autowired
//    private CourseRepository courseService;
//    @Autowired
//    private CoachRepository coachService;
//    @Autowired
//    private ManagerRepository managerService;
//    @Autowired
//    private QuestionRepository questionService;
//
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        loadGyms();
//        loadManager();
//        loadQuestion();
//        loadCourses();
//        loadCoach();
//        loadCourseTogym();
//    }
//
//    /***
//     * Method that preloads all the data of several gyms from a JSON file.
//     */
//    public void loadGyms(){
//        Gson gson = new Gson();
//        try (FileReader reader = new FileReader("src/main/java/com/cvgym/CVGYM/dataLoader/gym.json")) {
//
//            Gym[] gyms = gson.fromJson(reader, Gym[].class);
//
//            List<Gym> listaGyms = Arrays.asList(gyms);
//            for (Gym gym : listaGyms) {
//                gymService.save(gym);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /***
//     * Method that preloads all the data of several managers from a JSON file.
//     */
//    public void loadManager(){
//        Gson gson = new Gson();
//        try (FileReader reader = new FileReader("src/main/java/com/cvgym/CVGYM/dataLoader/manager.json")) {
//
//            Manager[] managers = gson.fromJson(reader, Manager[].class);
//            Long i = 1L;
//            List<Manager> listaManager = Arrays.asList(managers);
//            for (Manager manager : listaManager) {
//                managerService.save(manager);
//                i++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /***
//     * Method that preloads all the data of several questions from a JSON file.
//     */
//    public void loadQuestion(){
//        Gson gson = new Gson();
//        try (FileReader reader = new FileReader("src/main/java/com/cvgym/CVGYM/dataLoader/question.json")) {
//
//            Question[] questions = gson.fromJson(reader, Question[].class);
//            List<Question> listaQuestion = Arrays.asList(questions);
//            for (Question question : listaQuestion) {
//                questionService.save(question);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /***
//     * Method that preloads all the data of several courses from a JSON file.
//     */
//    public void loadCourses(){
//        Gson gson = new Gson();
//        try (FileReader reader = new FileReader("src/main/java/com/cvgym/CVGYM/dataLoader/course.json")) {
//
//            Course[] courses = gson.fromJson(reader, Course[].class);
//
//            List<Course> listCourses = Arrays.asList(courses);
//            for (Course course : listCourses) {
//                courseService.save(course);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /***
//     * Method that preloads all the data of several coaches from a JSON file.
//     */
//    public void loadCoach(){
//        Gson gson = new Gson();
//        try (FileReader reader = new FileReader("src/main/java/com/cvgym/CVGYM/dataLoader/coach.json")) {
//
//            Coach[] coaches = gson.fromJson(reader, Coach[].class);
//
//            List<Coach> listaCoaches = Arrays.asList(coaches);
//            int ngyms =gymService.findAll().size();
//            Long i =1L;
//
//            for (Coach coach : listaCoaches) {
//
//                coachService.save(coach);
//                i++;
//                if(i==ngyms+1L){
//                    i=1L;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /***
//     * Method that assigns a course (randomly) to a gym.
//     */
//    public void loadCourseTogym(){
//
//        int ncourses = courseService.findAll().size();
//        for( Gym g :gymService.findAll()){
//            Long id = new Random().nextLong(ncourses)+1;
//            g.getCourses().add(courseService.findById(id).get());
//
//            gymService.save(g);
//        }
//
//    }
//
//}
