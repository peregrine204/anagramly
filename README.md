# Anagramly
Program to check if words are anagrams! It works by taking two text inputs and checking if these are anagrams of each other.

# How to run
First, clone the project from git. From the cli of your choice run:

git clone https://github.com/peregrine204/anagramly.git

Once you have done this, you can build the project using maven:

mvn clean package (or install)

This will put the project into the target directory of the project as a jar file.

Then simply take the jar and run the following command in the same folder:

java -jar anagramly-1.0-SNAPSHOT.jar

This will launch the application.

# How it works

Anagramly is a CLI based anagram game which informs the user if two text inputs are anagrams. In addition, within the same session, a user can check if a word submitted has any matches for previously found anagrams.

Once the application is stopped, all data is removed.
