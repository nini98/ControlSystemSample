const fetchUtil = {

    handleResponse: async (response) => {
        const jsonData = await response.json();
        if (!response.ok) {
            const error = new Error('Network response was not ok');
            error.header = jsonData.header;
            error.data = jsonData.data;
            throw error;
        }
        return jsonData;
    },


    postJson: async (url, body) => {
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(body),
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    putJson: async (url, body) => {
        try {
            const response = await fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(body),
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    deleteJson: async (url, body) => {
        try {
            const response = await fetch(url, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(body),
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    getUrlEncoded: async (url, params) => {
        try {
            const queryString = new URLSearchParams(params).toString();
            const response = await fetch(`${url}?${queryString}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    postUrlEncoded: async (url, body) => {
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams(body),
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    putUrlEncoded: async (url, body) => {
        try {
            const response = await fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams(body),
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    deleteUrlEncoded: async (url, body) => {
        try {
            const response = await fetch(url, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams(body),
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },
};