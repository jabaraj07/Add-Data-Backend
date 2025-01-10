  const handledelete = ()=>{
     alert("Are you sure you want to delete your account? This action cannot be undone.");

     let response = prompt("Type 'DELETE' to confirm, or anything else to cancel:")

     if(response === null || response.trim().toLocaleLowerCase() !== 'delete' ){
          alert("Account deletion canceled.");
      return false;
     }
     else{
          return true;
     }

  }