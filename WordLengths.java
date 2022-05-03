
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        for(String s: resource.words()){
            int leng = s.length();
            if(Character.isLetter(s.charAt(0)) == false){
                leng -= 1;
            }
            if(Character.isLetter(s.charAt(s.length() - 1)) == false){
                leng -=1;
            }
            if(leng < 0){
                continue;
            }
            counts[leng]++;
        }
       
    }
    public void testCountWordLengths(){
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        for(int k = 0; k < 31; k++){
            if(counts[k] != 0){
                System.out.println(counts[k] + " words of length " + k);
            }
        }
        System.out.println(indexofMax(counts));
        //read words from file
        //count no of words of each length
        //store these in array counts
        //length 30 or more grouped together
        //don't count puntuation that are 1st and last chars of word
        
        //create a new array to store lengths
    }
    public int indexofMax(int[] values){
        int max = 0;
        for(int k = 0; k < values.length; k++){
            if(values[k] > max){
                max = k;
            }
        }
        return max;
    }
}
