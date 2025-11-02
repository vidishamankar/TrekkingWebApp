/* === TrekSafe Auth Script === */

document.addEventListener('DOMContentLoaded', () => {
    
    // --- 1. CATCH OAUTH REDIRECT ---
    const urlParams = new URLSearchParams(window.location.search);
    const tokenFromUrl = urlParams.get('token');

    if (tokenFromUrl) {
        localStorage.setItem('jwtToken', tokenFromUrl);
        // Clean the URL so the token isn't visible
        window.history.replaceState(null, null, window.location.pathname);
    }

    // --- 2. CHECK AUTH STATE & UPDATE UI ---
    checkAuthState();

    // --- 3. ADD LOGOUT LISTENER (Improved) ---
    const allDropdownItems = document.querySelectorAll('.logged-in-view .dropdown-item');
    allDropdownItems.forEach(item => {
        if (item.textContent === 'Logout') {
            item.addEventListener('click', (e) => {
                e.preventDefault(); // Stop the link from navigating
                logout();
            });
        }
    });
});

/**
 * Checks if a user is logged in (by checking localStorage)
 * and updates the navbar accordingly.
 */
function checkAuthState() {
    const token = localStorage.getItem('jwtToken');
    const loggedInViews = document.querySelectorAll('.logged-in-view');
    const loggedOutViews = document.querySelectorAll('.logged-out-view');

    if (!loggedInViews.length || !loggedOutViews.length) {
        console.error('Auth script could not find .logged-in-view or .logged-out-view elements.');
        return;
    }
    
    if (token) {
        // --- USER IS LOGGED IN ---
        loggedInViews.forEach(el => el.style.display = 'list-item');
        loggedOutViews.forEach(el => el.style.display = 'none');

        try {
            // Decode the token to get user info
            const payload = JSON.parse(atob(token.split('.')[1]));
            const userName = payload.name;
            const userPicture = payload.picture;

            const dropdownToggle = document.querySelector('.profile-dropdown-toggle');
            if (!dropdownToggle) return;

            dropdownToggle.innerHTML = ''; // Clear the default icon

            // 1. Add Profile Pic
            if (userPicture) {
                const profileImg = document.createElement('img');
                profileImg.src = userPicture;
                profileImg.alt = userName;
                profileImg.className = 'profile-pic-nav';
                dropdownToggle.appendChild(profileImg);
            } else {
                const defaultIcon = document.createElement('i');
                defaultIcon.className = 'bi bi-person-circle';
                defaultIcon.style.fontSize = '1.8rem';
                dropdownToggle.appendChild(defaultIcon);
            }

            // 2. Add User Name
            if (userName) {
                const nameSpan = document.createElement('span');
                nameSpan.className = 'd-none d-lg-inline-block ms-2';
                nameSpan.textContent = userName;
                dropdownToggle.appendChild(nameSpan);
            }

            // 3. Style the toggle button
            dropdownToggle.classList.add('btn', 'btn-outline-light', 'btn-sm', 'd-flex', 'align-items-center');
            dropdownToggle.style.borderRadius = '50px';
            dropdownToggle.style.padding = '0.25rem 0.5rem';
            dropdownToggle.style.paddingRight = '0.75rem';

        } catch (e) {
            console.error('Invalid token or failed to decode:', e);
            logout(); // The token is bad, so log the user out
        }

    } else {
        // --- USER IS LOGGED OUT ---
        loggedInViews.forEach(el => el.style.display = 'none');
        loggedOutViews.forEach(el => el.style.display = 'list-item');
    }
}

/**
 * Logs the user out by clearing the token and redirecting.
 */
function logout() {
    localStorage.removeItem('jwtToken');
    window.location.href = 'login.html';
}