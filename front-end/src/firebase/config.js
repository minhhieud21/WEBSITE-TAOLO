import firebase from 'firebase/compat/app';
import 'firebase/compat/auth';
import 'firebase/compat/firestore';
import 'firebase/compat/analytics';
const firebaseConfig = {
  apiKey: "AIzaSyDJUSDBm6OmEnpwxiFjgV6r93Vk3sXkw4M",
  authDomain: "ptudmnm.firebaseapp.com",
  projectId: "ptudmnm",
  storageBucket: "ptudmnm.appspot.com",
  messagingSenderId: "722320415100",
  appId: "1:722320415100:web:6efdf54f66d57b722c2cf9",
  measurementId: "G-FRBK74HL5H"
};

// Initialize Firebase
firebase.initializeApp(firebaseConfig);
firebase.analytics();

const auth = firebase.auth();
const db = firebase.firestore();

export { db, auth };
export default firebase;
