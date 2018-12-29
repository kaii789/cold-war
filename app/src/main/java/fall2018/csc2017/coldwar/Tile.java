package fall2018.csc2017.coldwar;

import java.io.Serializable;

import fall2018.csc2017.slidingtiles.R;

class Tile implements Serializable {
    private Agent agent;

    void setAgent(Agent agent) {
        this.agent = agent;
    }

    Agent getAgent(){
        return this.agent;
    }

    public Tile(Agent agent){
        this.agent = agent;
    }
}
