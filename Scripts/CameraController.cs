using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraController : MonoBehaviour {

    public GameObject player;
    private Vector3 offset;
    private Vector3 rotate;

	// Use this for initialization
	void Start () {
        	//offset = transform.position - player.transform.position;
		//transform.position = player.transform.position;

	}
	
	// LateUpdate runs after Update
	void LateUpdate () {
        	transform.position = player.transform.position;
		transform.Rotate(new Vector3(0.0f,player.GetComponent<PlayerController>().time(),0.0f)*Time.deltaTime);
	}
}
