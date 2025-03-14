package com.example.flagquizapp

class Flagsdao {

    fun bring5randomflags(db:DatabaseHelper):ArrayList<Flags>{
        val flagslist=ArrayList<Flags>()
        val db=db.writableDatabase
        val c=db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5",null)

        while (c.moveToNext()){
            val flag=Flags(c.getInt(c.getColumnIndexOrThrow("bayrak_id"))
                ,c.getString(c.getColumnIndexOrThrow("bayrak_ad"))
                ,c.getString(c.getColumnIndexOrThrow("bayrak_resim")))

           flagslist.add(flag)
        }
        return flagslist

    }
    fun bring3falsechoiceflags(db:DatabaseHelper,bayrak_id:Int):ArrayList<Flags>{
        val flagslist=ArrayList<Flags>()
        val db=db.writableDatabase
        val c=db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id!=$bayrak_id ORDER BY RANDOM() LIMIT 3",null)

        while (c.moveToNext()){
            val flag=Flags(c.getInt(c.getColumnIndexOrThrow("bayrak_id"))
                ,c.getString(c.getColumnIndexOrThrow("bayrak_ad"))
                ,c.getString(c.getColumnIndexOrThrow("bayrak_resim")))

            flagslist.add(flag)
        }
        return flagslist

    }

}