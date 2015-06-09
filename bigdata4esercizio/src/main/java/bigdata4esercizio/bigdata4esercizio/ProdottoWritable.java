package bigdata4esercizio.bigdata4esercizio;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class ProdottoWritable implements WritableComparable<ProdottoWritable> {
    private Text nomeCoppia;
    private IntWritable  quantita;
    
    
    
    public ProdottoWritable(){
    	
    }
    public ProdottoWritable(Text n,IntWritable q){
    	this.nomeCoppia=n;
    	this.quantita=q;
    	
    }
    
    
	public void readFields(DataInput in) throws IOException {
		this.nomeCoppia=new Text(in.readUTF());
		this.quantita=new IntWritable(in.readInt());
		
	}
     
	public void write(DataOutput out) throws IOException {
      out.writeUTF(this.nomeCoppia.toString());	
      out.writeInt(this.quantita.get());
	}

	public int compareTo(ProdottoWritable o) {
		return 0;
	}

	public Text getNomeCoppia() {
		return nomeCoppia;
	}

	public void setNomeCoppia(Text nomeCoppia) {
		this.nomeCoppia = nomeCoppia;
	}

	public IntWritable getQuantita() {
		return quantita;
	}

	public void setQuantita(IntWritable quantita) {
		this.quantita = quantita;
	}

}
