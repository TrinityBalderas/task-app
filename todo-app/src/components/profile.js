import React from 'react'
import { useAuth0 } from '@auth0/auth0-react'

const Profile = () => {
    const { user, isAuthenticated} = useAuth0();

  return (
    isAuthenticated && (
        <div>
            <img className="authImg" src={user.picture} alt={user.name} />
            <h2 className='user'>{user.name}</h2>
            <p className='user'>{user.email}</p>
            {/* {JSON.stringify(user, null, 2)} */}
        </div>
    )
  )
}

export default Profile