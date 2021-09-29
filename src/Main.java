
    // Alexis Ceballos Pelaez...

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

    public class Main {
        public static void main(String[] args) throws IOException {
            // reads a text file for the lsit of students..
            FileInputStream fileByteStream; // File input stream
            Scanner inputFile;                   // Scanner object
            fileByteStream = new FileInputStream("Input.txt");
            inputFile = new Scanner(fileByteStream);

            // outputs the list of students into a text file called output.txt
            // try to open file..
            FileOutputStream fileout = new FileOutputStream("output.txt");
            PrintWriter outFS = new PrintWriter(fileout);

            Random rand = new Random();
            // int Leader = rand.nextInt(3);


            // 3 different arrays.
            final int numStudents = 20;
            String[] names = new String[numStudents];// array for name(last name , first name middle name)
            String[] IDs = new String[numStudents]; // Array for ID's
            double[] Avg = new double[numStudents]; // Array for Avg of scores.
            int tries = 0;  //  how many times the while loop irritates after the last score is recieved.

            while (inputFile.hasNext()) {

                String Fname = inputFile.next();
                String Mname = inputFile.next();
                String Lname = inputFile.next();

                String name = Lname.concat(", " + Fname.concat(" " + Mname)); // puts last, first and middle into one.
                names[tries] = name;
                String ID = inputFile.next();
                IDs[tries] = ID;

                double sum = 0;
                for (int i = 0; i < 5; i++) {
                    sum += inputFile.nextInt();
                }
                double avg = sum / 5;
                Avg[tries] = avg;
                tries += 1;
            }
            //for loop swaps name, id, avg in alphabetical order..
            String nameholder;
            String idHolder;
            double AVG;

            for (int i = 0; i < tries; i++) {
                for (int j = i + 1; j < tries; j++) {
                    // the first and second letter of the last name of the first name
                    char a = names[i].charAt(0);
                    char b = names[i].charAt(1);
                    // the first and second letter of the last name of the second name
                    char c = names[j].charAt(0);
                    char d = names[j].charAt(1);

                    if ((int) a > (int) c) { //swap j with i if
                        // swaps name
                        nameholder = names[i];
                        names[i] = names[j];
                        names[j] = nameholder;
                        // ID swap
                        idHolder = IDs[i];
                        IDs[i] = IDs[j];
                        IDs[j] = idHolder;
                        // Avg swap
                        AVG = Avg[i];
                        Avg[i] = Avg[j];
                        Avg[j] = AVG;
                    } else if ((int) a == (int) c) {
                        if ((int) b < (int) d) {
                            // swaps name
                            nameholder = names[i];
                            names[i] = names[j];
                            names[j] = nameholder;
                            // ID swap
                            idHolder = IDs[i];
                            IDs[i] = IDs[j];
                            IDs[j] = idHolder;
                            // Avg swap
                            AVG = Avg[i];
                            Avg[i] = Avg[j];
                            Avg[j] = AVG;
                        } else
                            // swaps name
                            nameholder = names[j];
                        names[j] = names[i];
                        names[i] = nameholder;
                        // ID swap
                        idHolder = IDs[j];
                        IDs[j] = IDs[i];
                        IDs[i] = idHolder;
                        // Avg swap
                        AVG = Avg[j];
                        Avg[j] = Avg[i];
                        Avg[i] = AVG;
                    }
                }
            }
            for (int i = 0; i < tries; i++) { // sends the names, ID's, Averages to the output file..
                outFS.println(names[i]);
                outFS.println(IDs[i]);
                outFS.println(Avg[i]);
                outFS.println();

            }
            outFS.println("*************************************************************");
            outFS.println();

            int remainder = tries % 4; // remainder is the amount of students that are left after the students are divided by 4.
            int groups = tries / 4; //  creates the # of groups..

            // this checks how many students are left out of a group of 4 for the total students in the list.
            // if remainder is 1 - means that there is 1 student after everyone else is in a group of 4.
            if (remainder == 1) {

                for (int k = 0; k < groups; k++) { // the first for loop is for the groups
                    if (k == groups - 1) {
                        for (int t = 0; t < 5; t++) { //  for the students in the group.
                            if (t == 0) {
                                outFS.println("GROUP " + (k + 1)); //  k +1 is to tell the # of the group..
                                outFS.println(names[(k * 4) + t]); // k * 4 + t is to pick the student out of the array for names.

                            } else if (t == 4) {
                                outFS.println(names[(k * 4) + t]);
                                int Leaders = rand.nextInt(4);
                                outFS.println("Group " + (k + 1) + " 's leader: " + names[(k * 4) + Leaders]);

                                double Groupsavg = 0;
                                for (int s = 0; s < 5; s++) {
                                    Groupsavg += Avg[k * 4 + s];
                                }
                                outFS.println("Group average = " + (Groupsavg / 5));
                                outFS.println();

                            } else {
                                outFS.println(names[(k * 4) + t]);

                            }
                        }
                    } else {
                        for (int t = 0; t < 4; t++) {
                            if (t == 0) {
                                outFS.println("GROUP " + (k + 1));
                                outFS.println(names[(k * 4) + t]);

                            } else if (t == 3) {
                                outFS.println(names[(k * 4) + t]);
                                int Leader = rand.nextInt(3);
                                outFS.println("Group " + (k + 1) + " 's leader: " + names[(k * 4) + Leader]);
                                double Groupsavg = 0;
                                for (int s = 0; s < 4; s++) {
                                    Groupsavg += Avg[k * 4 + s];
                                }
                                outFS.println("Group average = " + (Groupsavg / 4));
                                outFS.println();

                            } else {
                                outFS.println(names[(k * 4) + t]);

                            }
                        }
                    }
                }

                // if remainder is bigger than 1 - means that there is 2 or more students after everyone else is in a group of 4.
            } else if (remainder > 1) {
                groups += 1;
                if (remainder == 2) {
                    // the first for loop is the amount of groups that it needs to make.
                    for (int k = 0; k < groups; k++) {
                        // this second for loop is the amount of students in the group.
                        if (k == (groups - 1)) {
                            for (int t = 0; t < 2; t++) {
                                if (t == 0) {
                                    outFS.println("GROUP " + (k + 1));
                                    outFS.println(names[(k * 4) + t]);

                                } else {
                                    outFS.println(names[(k * 4) + t]);
                                    int Leader = rand.nextInt(1);
                                    outFS.println("Group " + (k + 1) + " 's leader: " + names[(k * 4) + Leader]);

                                    double Groupsavg = 0;
                                    // this for loop adds the avg of the group.
                                    for (int s = 0; s < 2; s++) {
                                        Groupsavg += Avg[k * 4 + s];
                                    }
                                    outFS.println("Group average = " + (Groupsavg / 2));
                                    outFS.println();
                                }
                            }

                        } else {
                            for (int t = 0; t < 4; t++) {
                                if (t == 0) {
                                    outFS.println("GROUP " + (k + 1));
                                    outFS.println(names[(k * 4) + t]);

                                } else if (t == 3) {
                                    outFS.println(names[(k * 4) + t]);
                                    int Leader = rand.nextInt(3);
                                    outFS.println("Group " + (k + 1) + " 's leader: " + names[(k * 4) + Leader]);

                                    double Groupsavg = 0;
                                    // this for loop adds the avg of the group.
                                    for (int s = 0; s < 4; s++) {
                                        Groupsavg += Avg[k * 4 + s];
                                    }
                                    outFS.println("Group average = " + (Groupsavg / 4));
                                    outFS.println();

                                } else {
                                    outFS.println(names[(k * 4) + t]);
                                }
                            }
                        }
                    }
                    // ends the if statement for the remainder == 2...
                } else {
                    // the first for loop is the amount of groups that it needs to make.
                    for (int k = 0; k < groups; k++) {
                        // this second for loop is the amount of students in the group.
                        if (k == groups - 1) {
                            for (int t = 0; t < 3; t++) {
                                if (t == 0) {
                                    outFS.println("GROUP " + (k + 1));
                                    outFS.println(names[(k * 4) + t]);

                                } else if (t == 2) {
                                    outFS.println(names[(k * 4) + t]);
                                    int Leader = rand.nextInt(3);
                                    outFS.println("Group " + (k + 1) + " 's leader: " + names[(k * 4) + Leader]);

                                    double Groupsavg = 0;
                                    // this for loop adds the avg of the group.
                                    for (int s = 0; s < 3; s++) {
                                        Groupsavg += Avg[k * 4 + s];
                                    }
                                    outFS.println("Group average = " + (Groupsavg / 3));
                                    outFS.println();

                                } else {
                                    outFS.println(names[(k * 4) + t]);

                                }
                            }
                        } else {
                            for (int t = 0; t < 4; t++) {
                                if (t == 0) {
                                    outFS.println("GROUP " + (k + 1));
                                    outFS.println(names[(k * 4) + t]);

                                } else if (t == 3) {
                                    outFS.println(names[(k * 4) + t]);
                                    int Leader = rand.nextInt(3);
                                    outFS.println("Group " + (k + 1) + " 's leader: " + names[(k * 4) + Leader]);

                                    double Groupsavg = 0;
                                    // this for loop adds the avg of the group.
                                    for (int s = 0; s < 4; s++) {
                                        Groupsavg += Avg[k * 4 + s];
                                    }
                                    outFS.println("Group average = " + (Groupsavg / 4));
                                    outFS.println();

                                } else {
                                    outFS.println(names[(k * 4) + t]);

                                }
                            }
                        }
                    }

                }
                // this else statement is for the groups for which there is no students remaining.
            } else {
                for (int k = 0; k < groups; k++) {
                    for (int t = 0; t < 4; t++) {
                        if (t == 0) {
                            outFS.println("GROUP " + (k + 1));
                            outFS.println(names[(k * 4) + t]);

                        } else if (t == 3) {
                            outFS.println(names[(k * 4) + t]);
                            int Leader = rand.nextInt(3);
                            outFS.println("Group " + (k + 1) + " 's leader: " + names[(k * 4) + Leader]);

                            double Groupsavg = 0;
                            for (int s = 0; s < 4; s++) {
                                Groupsavg += Avg[k * 4 + s];
                            }
                            outFS.println("Group average = " + (Groupsavg / 4));
                            outFS.println();

                        } else {
                            outFS.println(names[(k * 4) + t]);
                        }
                    }
                }
            }
            outFS.close();// closes the output file..
        }

    }

