-- Inserts manager
INSERT INTO manager (last_name, name) VALUES ('Juan', 'Pérez García');
INSERT INTO manager (last_name, name) VALUES ('María', 'González López');
INSERT INTO manager (last_name, name) VALUES ('Carlos', 'Martínez Pérez');
INSERT INTO manager (last_name, name) VALUES ('Sofía', 'Hernández Ruiz');
INSERT INTO manager (last_name, name) VALUES ('Pedro', 'López Jiménez');

-- Inserts gym
INSERT INTO gym (city, ccaa, address, number, zip, phone_number, manager_id) VALUES ('Murcia', 'Región de Murcia', 'Calle Mayor', '10', '30001', '+34 968 123 456', 1);
INSERT INTO gym (city, ccaa, address, number, zip, phone_number, manager_id) VALUES ('Madrid', 'Comunidad de Madrid', 'Calle Gran Vía', '20', '28013', '+34 910 123 456', 2);
INSERT INTO gym (city, ccaa, address, number, zip, phone_number, manager_id) VALUES ('Barcelona', 'Cataluña', 'Avinguda Diagonal', '150', '08018', '+34 935 123 456', 3);
INSERT INTO gym (city, ccaa, address, number, zip, phone_number, manager_id) VALUES ('Sevilla', 'Andalucía', 'Calle Sierpes', '5', '41004', '+34 954 123 456', 4);
INSERT INTO gym (city, ccaa, address, number, zip, phone_number, manager_id) VALUES ('Valencia', 'Comunidad Valenciana', 'Calle Colón', '30', '46004', '+34 963 123 456', 5);

-- Inserts question
INSERT INTO question (mail, subject, description, type_question) VALUES ('juan.perez@example.com', 'Consulta de horarios', 'Quisiera saber si tienen clases los sábados por la mañana.', 'Comentarios');
INSERT INTO question (mail, subject, description, type_question) VALUES ('maria.gonzalez@example.com', 'Membresías', 'Estoy interesado en obtener una membresía y quisiera saber cuáles son los planes disponibles.', 'Más información');
INSERT INTO question (mail, subject, description, type_question) VALUES ('carlos.martinez@example.com', 'Renovación de contrato', 'Mi contrato está por vencer y quisiera saber cuáles son las opciones de renovación disponibles.', 'Dudas sobre tu contrato');
INSERT INTO question (mail, subject, description, type_question) VALUES ('sofia.hernandez@example.com', 'Sugerencia de mejoras', 'Me gustaría proponer algunas mejoras para la sala de pesas, ¿a dónde puedo enviar mis sugerencias?', 'Comentarios');
INSERT INTO question (mail, subject, description, type_question) VALUES ('pedro.lopez@example.com', 'Problema con el equipo', 'El equipo de cardio número 5 parece estar descompuesto, ¿pueden enviar a alguien a revisarlo?', 'Otros');

-- Inserts coach
INSERT INTO coach (name, last_name, gym_id) VALUES ('Juan', 'Pérez García', 1);
INSERT INTO coach (name, last_name, gym_id) VALUES ('María', 'González López', 1);
INSERT INTO coach (name, last_name, gym_id) VALUES ('Carlos', 'Martínez Pérez', 1);
INSERT INTO coach (name, last_name, gym_id) VALUES ('Sofía', 'Hernández Ruiz', 2);
INSERT INTO coach (name, last_name, gym_id) VALUES ('Pedro', 'López Jiménez', 2);
INSERT INTO coach (name, last_name, gym_id) VALUES ('Lucía', 'Rodríguez Gómez', 2);
INSERT INTO coach (name, last_name, gym_id) VALUES ('Ana', 'Sánchez Rodríguez', 3);
INSERT INTO coach (name, last_name, gym_id) VALUES ('Miguel', 'García Fernández', 3);
INSERT INTO coach (name, last_name, gym_id) VALUES ('Carmen', 'Fernández Pérez', 3);
INSERT INTO coach (name, last_name, gym_id) VALUES ('Diego', 'Alvarez García', 4);
INSERT INTO coach (name, last_name, gym_id) VALUES ('Julia', 'Ruiz López', 4);
INSERT INTO coach (name, last_name, gym_id) VALUES ('Jorge', 'Morales Hernández', 4);
INSERT INTO coach (name, last_name, gym_id) VALUES ('Laura', 'Gómez Martínez', 5);
INSERT INTO coach (name, last_name, gym_id) VALUES ('Adrián', 'Pérez Sánchez', 5);
INSERT INTO coach (name, last_name, gym_id) VALUES ('Elena', 'Romero Fernández', 5);


-- Inserts course
INSERT INTO course (name, intensity, description) VALUES ('Yoga', 1, 'Un entrenamiento de baja intensidad que enfatiza la respiración, la flexibilidad y la relajación.');
INSERT INTO course (name, intensity, description) VALUES ('Entrenamiento Interválico de Alta Intensidad (HIIT)', 5, 'Un entrenamiento de alta intensidad que implica breves ráfagas de ejercicio intenso seguido de períodos de descanso.');
INSERT INTO course (name, intensity, description) VALUES ('Ciclismo', 4, 'Un ejercicio cardiovascular que implica andar en bicicleta estacionaria.');
INSERT INTO course (name, intensity, description) VALUES ('Natación', 3, 'Un ejercicio cardiovascular de bajo impacto que implica nadar vueltas.');
INSERT INTO course (name, intensity, description) VALUES ('Levantamiento de pesas', 2, 'Un ejercicio de fuerza que implica levantar pesas para construir músculo y mejorar la postura.');
INSERT INTO course (name, intensity, description) VALUES ('Baile aeróbico', 3, 'Un ejercicio cardiovascular que implica bailar al ritmo de la música.');

-- Inserts tabla intermedia gym_courses
INSERT INTO gym_courses (gyms_id, courses_id) VALUES (1, 1);
INSERT INTO gym_courses (gyms_id, courses_id) VALUES (1, 2);

INSERT INTO gym_courses (gyms_id, courses_id) VALUES (2, 3);
INSERT INTO gym_courses (gyms_id, courses_id) VALUES (2, 4);

INSERT INTO gym_courses (gyms_id, courses_id) VALUES (3, 5);
INSERT INTO gym_courses (gyms_id, courses_id) VALUES (3, 6);

INSERT INTO gym_courses (gyms_id, courses_id) VALUES (4, 1);
INSERT INTO gym_courses (gyms_id, courses_id) VALUES (4, 3);
INSERT INTO gym_courses (gyms_id, courses_id) VALUES (4, 5);

INSERT INTO gym_courses (gyms_id, courses_id) VALUES (5, 2);
INSERT INTO gym_courses (gyms_id, courses_id) VALUES (5, 4);
INSERT INTO gym_courses (gyms_id, courses_id) VALUES (5, 6);



