using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System;

public class PlayerController : MonoBehaviour {

    public float speed;
   
    private Rigidbody rb;

    private float tiltAroundX;

    public float angleBetween = 0.0f;
    public Transform target;

    private float moveHorizontal;
    private float moveVertical;

    public GameObject Camera;

    private float angle;
    private float positiveAngle;

    public float Rspeed;
  
    private void Start()
    {
        rb = GetComponent<Rigidbody>();

    }
    
    void Update()
    {
        angle = Camera.GetComponent<Camera>().transform.eulerAngles.y;
        
        Debug.Log("Angle: "+angle.ToString());

        positiveAngle = angle/180*Mathf.PI;
        moveHorizontal = Input.GetAxis("Horizontal");
        moveVertical = Input.GetAxis("Vertical");

        Vector3 targetDir = target.position - transform.position;
        angleBetween = Vector3.Angle(transform.forward, targetDir);
    }

    //called before performing any physics operations - where most physics code will go
    void FixedUpdate()
    {
        

        Vector3 movement = new Vector3 (moveVertical*Mathf.Sin(positiveAngle),0.0f,moveVertical*Mathf.Cos(positiveAngle));

        Debug.Log("Cos: " + Mathf.Cos(positiveAngle) + "Sin: " + Mathf.Sin(positiveAngle));
        rb.AddForce(movement*speed);

        tiltAroundX = moveHorizontal * Rspeed;
        transform.eulerAngles = new Vector3(0.0f, moveHorizontal, 0.0f);
    }
    public float time (){
        return tiltAroundX;
    }

} 