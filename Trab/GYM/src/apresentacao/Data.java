package apresentacao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Data
{   Date data_atual;
    String dt;

    public Data()
    {   data_atual = new Date();
        SimpleDateFormat theFormat = new SimpleDateFormat("dd/MM/yyyy");
        dt = theFormat.format(data_atual);
    }

    public String getData()
    {   return dt;  }

}