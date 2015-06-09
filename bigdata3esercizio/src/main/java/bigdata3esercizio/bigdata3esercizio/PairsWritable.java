package bigdata3esercizio.bigdata3esercizio;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class PairsWritable implements WritableComparable<PairsWritable> {
private Text leftElement;
private Text rightElement;


public PairsWritable(){
	
}
public int compare(WritableComparable w1, WritableComparable w2){
	PairsWritable k1 = (PairsWritable) w1;
	PairsWritable k2 = (PairsWritable) w2;
    return k1.compareTo(k2);
}

public void readFields(DataInput in) throws IOException {
	this.leftElement=new Text(in.readUTF());
    this.rightElement=new Text(in.readUTF());
	
}

public void write(DataOutput out) throws IOException {
	out.writeUTF(this.leftElement.toString());
	out.writeUTF(this.rightElement.toString());
	
}
public int hashCode() {
    return (this.leftElement.hashCode() + this.rightElement.hashCode());
}
@Override
public boolean equals(Object t) {
	PairsWritable o=(PairsWritable)t;
	if((this.leftElement.toString().equals(o.leftElement.toString())&&this.rightElement.toString().equals(o.rightElement.toString()))||(this.leftElement.toString().equals(o.rightElement.toString())&&this.rightElement.toString().equals(o.leftElement.toString())))
	return true;
	else
		return false;
	
	
	
}


public int compareTo(PairsWritable tp) {
	
	
    int cmp = leftElement.toString().compareTo(tp.leftElement.toString());
    if (cmp > 0) {
    	int cmp2 = leftElement.toString().compareTo(tp.rightElement.toString());
    	if (cmp2 != 0){
    		return cmp;
    	}
    	int finalCmp = rightElement.toString().compareTo(tp.leftElement.toString());
        	if (finalCmp == 0){
        		return finalCmp;
        	}
    	return cmp; 
    }
    if (cmp < 0) {
    	int cmp2 = leftElement.toString().compareTo(tp.rightElement.toString());
    	if (cmp2 != 0){
    		return cmp;
    	}
    	int finalCmp = rightElement.toString().compareTo(tp.leftElement.toString());
    	if (finalCmp == 0){
    		return finalCmp;
    	}
	return cmp; 
    }
    
    return rightElement.toString().compareTo(tp.rightElement.toString());
}






public String toString() {
    return this.leftElement+ " " + this.rightElement;
}

public Text getCoppia1() {
	return leftElement;
}

public void setCoppia1(Text coppia1) {
	this.leftElement = coppia1;
}

public Text getCoppia2() {
	return rightElement;
}

public void setCoppia2(Text coppia2) {
	this.rightElement = coppia2;
}
}
