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

    public Question createQuestion(Question question){
        long tem = id.incrementAndGet();
        question.setId(tem);
        questions.put(tem, question);
        return question;
    }

    public Question putQuestion(Long id, Question question) {
        question.setId(id);
        return questions.put(id, question);

    }

    public Collection<Question> getAllQuestions(){
        return questions.values();
    }

    public Optional<Question> findById(Long id) {

        if (containsKey(id)) {
            Question q = questions.get(id);
            return Optional.of(q);
        } else {
            return Optional.empty();
        }

    }
    public boolean containsKey(Long id) {
        return questions.containsKey(id);
    }

    public boolean deleteQuestion(Long id) {
        if (containsKey(id)) {
            questions.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
