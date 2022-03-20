/*
time: O(N)
space :O(N) stack formed for the operators

approach: stack stores the operators + and - with sign to the numbers but + in the Stack.

Whenever we see * or / we remove the two numbers one prev and the other next
perform the operation and then put it back into the numbers array.
WE have to take care of spaces in between and the negative signs.

Optimal way could be to store numbers in a variable and whenever multiplication or
division comes up perform the operation and then update this variable which will
save O(n) space.

*/


class Solution {
    int count = 0;
    public int calculate(String s) {

        Stack<Character> st = new Stack<>();

        List<Integer> numbers = new ArrayList<>();

        int curr = 0 ;

        int result = 0;

        while(count < s.length()){

            if(Character.isDigit(s.charAt(count))){
                int num = returnNumber(count,s.length(),s);
                numbers.add(curr,num);
                curr++;
            }
            else if(s.charAt(count) == '*'){
                int temp = numbers.get(curr-1);
                int num = returnNumber(count++, s.length(),s);
                --curr;
                numbers.set(curr++, temp * num);
            }
            else if(s.charAt(count) == '/'){
                int temp = numbers.get(curr-1);
                int num = returnNumber(count++, s.length(),s);
                --curr;
                numbers.set(curr++, temp / num);
            }
            else if(s.charAt(count) == '+'){
                st.push(s.charAt(count++));
            }
            else if(s.charAt(count) == '-'){
                st.push('+');
                int num = returnNumber(count++,s.length(),s);
                numbers.add(-1 * num);
                curr++;
            }else{
                count++;
            }

        }

        result = numbers.get(numbers.size() - 1);
        int adding  = numbers.size() - 2;
        while(!st.isEmpty() && adding >= 0){
                result += numbers.get(adding--);
        }

        return result;

    }

    private int returnNumber(int start, int end, String s){

        int st = count;

        while(count < end && s.charAt(count) == ' '){
            count++;
        }

        st = count;

        while(count < end && Character.isDigit(s.charAt(count))){
                    count++;
        }

        if(st == count){
            return Integer.valueOf(s.charAt(st));
        }
        return Integer.valueOf(s.substring(st, count));



    }
}
