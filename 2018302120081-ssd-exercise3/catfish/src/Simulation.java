import java.util.Vector;
public class Simulation {
    private final int maxRow;
    private final int maxColumn;
    private Vector catfishes;
    public Simulation(int maxRow,int maxColumn){
        this.maxRow=maxRow;
        this.maxColumn=maxColumn;
        this.catfishes=new Vector(50);
    }
    public int getMaxRow(){
        return this.maxRow;
    }
    public int getMaxColumn(){
        return this.maxColumn;
    }
    public void addCatfish(Catfish paramCatfish){
        if(paramCatfish == null){
            return;
        }
        if(this.catfishes.contains(paramCatfish)){
            return;
        }
        this.catfishes.add(paramCatfish);
    }
    public Catfish getCatfish(int paramInt){
        return (Catfish)this.catfishes.get(paramInt);
    }
    public Vector getNeighbors(int paramInt1,int paramInt2,int paramInt3){
        Vector vector=new Vector();
        for (int i=0;i<this.catfishes.size();i++){
            Catfish catfish=(Catfish)this.catfishes.get(i);
            if((catfish!=null)&&(catfish.getColumn()<=paramInt2+paramInt3)&&(catfish.getColumn()>=paramInt2-paramInt3)){
                vector.add(catfish);
            }
        }
        return vector;
    }
}
