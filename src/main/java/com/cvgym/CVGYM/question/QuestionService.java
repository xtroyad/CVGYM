package com.cvgym.CVGYM.question;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class QuestionService {
    private Map<Long, Question> questions = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();

    /***
     * Method that creates a question
     * @param question
     * @return the question created
     */
    public Question createQuestion(Question question){
        long tem = id.incrementAndGet();
        question.setId(tem);
        questions.put(tem, question);
        return question;
    }

    /***
     * Method that gives you the collection of questions
     * @return the entire collection of existing questions
     */
    public Collection<Question> getAllQuestions(){
        return questions.values();
    }

    /***
     * Method that searches through all questions and returns the one with the sent id
     * @param id
     * @return question with this id
     */
    public Optional<Question> findById(Long id) {

        if (containsKey(id)) {
            Question q = questions.get(id);
            return Optional.of(q);
        } else {
            return Optional.empty();
        }

    }

    /***
     * Method that returns true if this map maps one or more keys to the specified value
     * @param id
     * @return a boolean value equal to true if there is a trainer with that id
     */
    public boolean containsKey(Long id) {
        return questions.containsKey(id);
    }

    /***
     * Method that updates a question's information
     * @param id
     * @param question
     * @return the value to which this map previously associated the key, or null if the map contained no mapping for the key.
     */
    public Question putQuestion(Long id, Question question) {
        question.setId(id);
        return questions.put(id, question);

    }

    /***
     * Method that removes a question from the question's map
     * @param id
     * @return true or false depending on whether the deletion was successful or not.
     */
    public boolean deleteQuestion(Long id) {
        if (containsKey(id)) {
            questions.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
