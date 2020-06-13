package com.example.cmpt276a2.model;

import java.util.ArrayList;
import  java.util.Iterator;
import java.util.List;

public class lensManager implements  Iterable<lens>{
    private List<lens> lensArray = new ArrayList<>();

     public void add(lens Lens){
      lensArray.add(Lens);
    }

    @Override
    public Iterator<com.example.cmpt276a2.model.lens> iterator() {
        return lensArray.iterator();
    }
}
