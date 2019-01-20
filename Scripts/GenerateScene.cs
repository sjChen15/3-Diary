using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System;
using System.IO;

public class GenerateScene : MonoBehaviour
{
    string line;
    string[] info; 
    //public Text outputText;
    public Transform prefab;

    // Start is called before the first frame update
    void Start()
    {
        try {
            StreamReader sr = new StreamReader(@"C:\Users\MLH Admin\Documents\3-Diary\Assets\TextFiles\test.txt");
            //line = sr.ReadLine();
            //utputText.text = line;
             
            while (sr.ReadLine() != null) {
                line += ","+sr.ReadLine();
                info = line.Split(',');
                //outputText.text = line;
           }

        }
        catch(Exception e) {}

        makeObject();
    }

    // Update is called once per frame
    void Update()
    {
        //outputText.text = line;
    }

    void makeObject() {
        //System.Random rand = new System.Random();
        for (int i = 0; i < 10; i++){
            //Instantiate(prefab, new Vector3(rand.Next(200,800),rand.Next(0,50),rand.Next(200,300)),Quaternion.identity);
            Instantiate(prefab, new Vector3(i*30.5F,0,i*70.0F),Quaternion.identity);
        }
    }
}
