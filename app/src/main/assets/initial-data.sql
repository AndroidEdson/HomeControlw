   INSERT INTO users (id, name, lastname, email, pass) VALUES (0,'Armin', 'Aguilar', 'armin@gmail.com', '12340');
   INSERT INTO users (id, name, lastname, email, pass) VALUES (1,'Josue', 'Espadas', 'espadas@gmail.com', '00000');

   INSERT INTO profiles (id, description, actuators ) VALUES ( 0,'Día Lluvioso', 'JKL1250L2250B101B21T250R1FFFFFFR2FFFFFF');
   INSERT INTO profiles (id, description, actuators ) VALUES ( 1,'Día Soleado', 'JKL1250L2250B101B21T250R1FFFFFFR2FFFFFF');
   INSERT INTO profiles (id, description, actuators ) VALUES ( 2,'Noche', 'JKL1250L2250B101B21T250R1FFFFFFR2FFFFFF');
   INSERT INTO profiles (id, description, actuators ) VALUES (3,'Atardecer', 'JKL1250L2250B101B21T250R1FFFFFFR2FFFFFF');

    INSERT INTO users_profile (id_user, id_profile ) VALUES ( 0, 0);
    INSERT INTO users_profile (id_user, id_profile ) VALUES ( 0, 1);
    INSERT INTO users_profile (id_user, id_profile ) VALUES ( 0, 2);
    INSERT INTO users_profile (id_user, id_profile ) VALUES ( 0, 3);
    INSERT INTO users_profile (id_user, id_profile ) VALUES ( 1, 0);
    INSERT INTO users_profile (id_user, id_profile ) VALUES ( 1, 3);

    INSERT INTO pass_door(id, NIP) VALUES (0, 1245);