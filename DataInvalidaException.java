package TrabalhoFinalPOO;

public class DataInvalidaException extends Exception{
    public DataInvalidaException(String mensagem){
        super(mensagem);
    }

    public static boolean validarData(String data){
        String[] messes = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        boolean mesExistente = false;

        for(int i = 0; i < data.length(); i++){
            if(i == 2 || i == 5){
                if(data.charAt(i) != '/')
                    return false;
            } else{
                if(data.charAt(i) < '0' || data.charAt(i) > '9')
                    return false;
            }
        }

        for(int i = 0; i < 12; i++){
            if(data.regionMatches(3, messes[i], 0, 2)){
                mesExistente = true;
                if(i == 0 || i == 2 || i == 4 || i == 6 || i == 7 || i == 9 || i == 11){
                    if(data.charAt(0) > '3' || (data.charAt(0) == '3' && data.charAt(1) > '1'))
                        return false;
                }
                else if(i == 3 || i == 5 || i == 8 || i == 10){
                    if(data.charAt(0) > '3' || (data.charAt(0) == '3' && data.charAt(1) > '0'))
                        return false;
                }
                else{
                    if(data.charAt(0) > '2' || (data.charAt(0) == '2' && data.charAt(1) > '8'))
                        return false;
                }
                break;
            }
        }
        if(mesExistente == false)
            return false;

        return true;
    }
}
