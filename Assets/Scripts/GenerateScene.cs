using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using System.IO;

public class GenerateScene : MonoBehaviour
{
    string line;
    public Text outputText;

    // Start is called before the first frame update
    void Start()
    {
        try {
            StreamReader sr = new StreamReader("TextFiles/test.txt");
            line = sr.ReadLine();

            while (line != null) {
                //Console.WriteLine(line);
                line = sr.ReadLine();
                outputText.text = line;
            
        }
        catch(Exception e) {}
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
