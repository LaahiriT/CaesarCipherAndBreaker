
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (Character.isUpperCase(currChar) == true){
                int idx = alphabet.toUpperCase().indexOf(currChar);
                if (idx != -1){
                    char newChar = shiftedAlphabet.toUpperCase().charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else{
                int idx = alphabet.indexOf(currChar);
                if (idx != -1){
                    char newChar = shiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }

    //count the letters occurences
    public int[] countLetters(String message){
        //need a array to store the counts of letter occurences
        int[] counts = new int[25];
        //for each letter in the message: look at the letter and increment corresponding 
        //counter
        for(int i = 0; i <message.length(); i++){
            char upp = Character.toUpperCase(message.charAt(i));
            int dex = alphabet.indexOf(upp);
            if(dex != -1){
                counts[dex] +=1;
            }
            
        }
        return counts;
    }
    public int maxIndex(int[] vals){
        int max = 0;
        //from vals, we need to find the max index
        //for each index in the array
        //check if the index is greater than max
        //if so make that the max
        //return max at the end
        for(int k = 0; k < vals.length; k++){
            if(vals[k] > vals[max]){
                max = k;
            }
        }
        return max;
    }
    public String decrypt(String encryptedmsg, int key){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        int[] freqs = countLetters(encryptedmsg);
        int max = maxIndex(freqs);
        int dkey = max - 4;
        if(max < 4){
            dkey = 26 - (4 - max);
        }
        return cc.encrypt(encryptedmsg, 26-dkey);
    }
    public void testDecrypt(){
        String enc = "abcde";
        int key = 4;
        System.out.println(decrypt(enc, key));
    }
    public String halfOfString(String message, int start){
        StringBuilder newStr = new StringBuilder();
        char validChar = ' ';
        //for all characters in string
        for(int i = 0; i < message.length(); i++){
        //If the index of the char is % 2
            
            if(start % 2 == 0 && i % 2 ==0){
             //add it to the new string
                
                validChar = message.charAt(i);
                newStr.append(validChar);
            }
            if(start % 2 != 0 && i%2 != 0){
                validChar = message.charAt(i);
                newStr.append(validChar);
            }
            
        }
        return newStr.toString();
         
    }
    public int getKey(String s){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] count = countLetters(s);
        int idx = 0;
        for(int i = 0; i < count.length; i++){
            if(count[i] > idx){
                idx = i;
            }
        }
        return idx;
        
    }
    public String decryptTwoKeys(String encrypted){
        String first = halfOfString(encrypted, 0);
        String second = halfOfString(encrypted, 1);
        String decrypt1 = decrypt(first, 2);
        String decrypt2 = decrypt(second, 20);
        int key1 = getKey(decrypt1);
        int key2 = getKey(decrypt2);
        System.out.println("decrypt1: " + decrypt1 + " and the key is: " + key1);
        System.out.println("decrypt2: " + decrypt2 + " and the key is: " + key2);
        StringBuilder sb = new StringBuilder();
        int a = 0;
        int b = 0;
        for(int i=0; i < decrypt1.length()+decrypt2.length(); i++){
            if(i % 2 == 0){
                sb = sb.append(decrypt1.charAt(a));
                a++;
              
            }
            if(i % 2 != 0){
                sb = sb.append(decrypt2.charAt(b));
                b++;
            }
        }
        return sb.toString();
    }
    
}
