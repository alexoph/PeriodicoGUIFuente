var int: lectores;

var int: x1;
var int: x2;
var int: x3;
var int: x4;
var int: x5;
var bool: s1;
var bool: s2;
var bool: s3;
var bool: s4;
var bool: s5;

constraint s1*x1 + s2*x2 + s3*x3 + s4*x4 + s5*x5 =10;
constraint x1 <= 9;
constraint x1 >= 5;
constraint x2 <= 7;
constraint x2 >= 4;
constraint x3 <= 5;
constraint x3 >= 2;
constraint x4 <= 4;
constraint x4 >= 2;
constraint x5 <= 3;
constraint x5 >= 1;

constraint lectores = 1500*s1*x1 + 2000*s2*x2 + 1000*s3*x3 + 1500*s4*x4 + 750*s5*x5;
solve maximize lectores;
output ["\nX1=", show(x1), "\t S=", show(s1),"\nX2=", show(x2), "\t S=", show(s2),"\nX3=", show(x3), "\t S=", show(s3),"\nX4=", show(x4), "\t S=", show(s4),"\nX5=", show(x4), "\t S=", show(s5),"\nLectores=", show(lectores)]