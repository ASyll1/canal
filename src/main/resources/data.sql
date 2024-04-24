INSERT INTO Meeting_Room (name, capacity,has_screen, has_webcam,has_table,has_octopus)
 VALUES
 ('E1001', 23, false,false,false,false),('E1002', 10, true,false,false,false),
 ('E1003', 8, false,false,false,true),('E1004', 4, false,false,true,false),
 ('E2001', 4, false,false,false,false),('E2002', 15, true,true,false,false),
 ('E2003', 7, false,false,false,false),('E2004', 9, false,false,true,false),
 ('E3001', 13, true,true,false,true),('E3002', 8, false,false,false,false),
 ('E3003', 9, true,false,false,true),('E3004', 4, false,false,false,false);

 INSERT INTO Equipment (name, quantity) VALUES
 ('screen',5),('octopus',4),('webcam',4),('table',2);
