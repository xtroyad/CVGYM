package com.cvgym.CVGYM.question;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
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

    public Collection<Question> getAllQuestions(){
        return questions.values();
    }
}
